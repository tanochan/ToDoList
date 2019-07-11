ToDoList
===
## URL
http://tomcat.ikiri-switch.com/

## 開発環境
* macOS 10.14.4
* VSCode 1.35.1

## 使用技術
* Java 1.8.0_77
* SpringInitializr
* Spring Boot 2.1.6
    * JPA
    * Thymeleaf 3.0.4
    * DevTools
* Gradle 5.5
* MySQL 5.6.42(ローカル) 5.7.26(サーバー)
* Apache Tomcat 9.0.21
* Amazon Linux release 2 (Karoo)

## 環境セットアップ手順
### ローカル
[SpringInitializr](https://start.spring.io/)を用いてプロジェクトを作成
```
* Project： Gradle Project
* Language： Java
* Spring Boot： 2.1.6
* Project Metadata
    * Group： com.example
    * Artifact: todolist
* Dependencies： Web, JPA, Thymeleaf, DevTools, MySQL 
```
Javaコードのビルド
```
$ gradle build
```
プロジェクトの実行を行い、動作確認
```
$ gradle bootRun
```
### サーバー
AWSのEC2インスタンスにTomcatを構築し、spring bootで生成したWARファイルをwebapps配下に置き、デプロイを行う。
新たにt2.smallインスタンスを作成し行った。
* nginxの導入
```
$ sudo yum install nginx
```

* Javaの導入
```
$ sudo yum install -y java-1.8.0-openjdk
$ sudo yum install -y java-1.8.0-openjdk-devel
```

* Tomcatの導入
```
# Tomcatユーザーを作成し、ダウンロード
$ useradd -s /sbin/nologin tomcat
$ cd ~
$ wget http://ftp.meisei-u.ac.jp/mirror/apache/dist/tomcat/tomcat-9/v9.0.21/bin/apache-tomcat-9.0.21.tar.gz

# 解凍し、配置
$ tar -xzvf ~/apache-tomcat-9.0.21.tar.gz
$ mv ~/apache-tomcat-9.0.21 /opt
$ chown -R tomcat:tomcat /opt/apache-tomcat-9.0.21

# Tomcatのシンボリックリンク作成
$ ln -s /opt/apache-tomcat-9.0.21 /opt/apache-tomcat
$ chown -h tomcat:tomcat /opt/apache-tomcat

# ログシンボリックリンク作成
$ ln -s /opt/apache-tomcat/logs /var/log/tomcat
$ chown -h tomcat:tomcat /var/log/tomcat
```
TomcatをOSにサービスとして登録する為、ルート権限でUnitを作成
`/usr/lib/systemd/system/tomcat.service`を新規作成
```
# Systemd unit file for default tomcat
#
# To create clones of this service:
# DO NOTHING, use tomcat@.service instead.

[Unit]
Description=Apache Tomcat Web Application Container
After=syslog.target network.target

[Service]
Type=oneshot
PIDFile=/opt/apache-tomcat/tomcat.pid
RemainAfterExit=yes
#EnvironmentFile=/etc/tomcat/tomcat.conf
#Environment="NAME="
#EnvironmentFile=-/etc/sysconfig/tomcat
ExecStart=/opt/apache-tomcat/bin/startup.sh
ExecStop=/opt/apache-tomcat/bin/shutdown.sh
ExecReStart=/opt/apache-tomcat/bin/shutdown.sh;/opt/apache-tomcat/bin/startup.sh
SuccessExitStatus=143
User=tomcat
Group=tomcat

[Install]
WantedBy=multi-user.target
```
起動確認
```
$ systemctl start tomcat.service
$ systemctl status tomcat.service
```

* MySQL5.7の導入
```
#mysql8.0リポジトリの追加（このリポジトリに5.7も含まれている）
$ sudo yum localinstall https://dev.mysql.com/get/mysql80-community-release-el7-1.noarch.rpm -y

#mysql8.0リポジトリの無効化
$ sudo yum-config-manager --disable mysql80-community

#mysql5.7リポジトリの有効化
$ sudo yum-config-manager --enable mysql57-community

#mysql5.7がインストールできるか確認
$ sudo yum info mysql-community-server

#mysqlインストール
$ sudo yum install mysql-community-server -y

#自動起動設定
$ sudo systemctl start mysqld.service
$ sudo systemctl enable mysqld.service
$ systemctl status mysqld.service
```


## DB構成
| id | todo_name | deadline | status | created_at |
| -------- | -------- | -------- |-------- |-------- |
| INT(11)     | VARCHAR(255)     | VARCHAR(255)    | TINYINT(1)     | TIMESTAMP |
* id・・・リストID
* todo_name・・Todo名
* deadline・・・Todoの期限日
* created_at・・・Todoの作成日

## エンドポイント
ユーザーがアクセスするURLに紐づくメソッドを作成する。
作成するエンドポイントは以下の8種類

| HTTPメソッド | URL | Controllerのメソッド | 概要 |
| -------- | -------- | -------- |-------- |
| GET     | /    | topView()    | Todoリスト一覧の表示 |
| POST    | /   | addTodo()    | Todoの追加 |
| POST    | /edit   | editTodo()   | 編集画面の表示 |
| POST    | /edit/update   | updateTodo()    | Todoデータの更新 |
| POST    | /switch/complete   | updateComplete()    | 完了ステータスに変更 |
| POST    | /switch/incomplete   | updateIncomplete()    | 未完了ステータスに変更 |
| GET    | /search   | searchView()    | 検索画面の表示 |
| POST    | /search   | searchResult()    | Todoの検索 |



## 設計
* **Entity**
データベースに登録する項目を定義する。
`src/main/java/com/example/ToDoList/entities`に`ToDoItem.java`を作成した。

* **Repository**
JPAのJpaRepositoryを継承したinterfaceを作成する。ここでは、findAllやsave等用意されている典型的な操作以外のことを行うメソッドを定義する。
`src/main/java/com/example/ToDoList/repositories`に`ToDoItemRepository.java`を作成した。

* **Service**
JPAのメソッドを使わずに、ここでCRUDに必要なメソッドを定義する。
`src/main/java/com/example/ToDoList/service`に`ToDoItemService.java`を作成した。

* **Controller**
`src/main/java/com/example/ToDoList/controller`に`ToDoItemController.java`を作成した。

* **View**
各画面のテンプレートを作成する
`src/main/resources/templates`に以下の3画面分のファイルを作成した。
＊ トップ画面`topPage.html`
＊ 編集画面`editPage.html`
＊ 検索画面`searchPage.html`
また、`src/main/resources/templates/commons`に共通ヘッダー`header.html`を作成

## 未実装
* 作成するToDo名が既に存在するときのエラーメッセージの表示
 
## 改善箇所
* 文字数のチェックのエラーメッセージの表示
javascriptを用いてalertで実装しているが、alertを用いずに実装すべき。
# AWS Serverless User API — Test Automation

Kısa açıklama
- Bu dizin API testleri, local mock verisi (db.json) ve Postman koleksiyonunu içerir.
- Testler RestAssured + JUnit5 ile yazıldı ve Maven ile çalıştırılır.

Önkoşullar
- Java 17 (projede kaynak/target 17). Homebrew/Temurin önerilir:
  export JAVA_HOME=$(/usr/libexec/java_home -v17)
- Maven (3.9+)
- Node.js + npm (json-server için)
- (İsteğe bağlı) newman (Postman koleksiyonunu CLI ile çalıştırmak için)

Dosya/konumlar
- src/test/java/api /* JUnit testleri (GetUserTest, AddUserTest, UpdateUserTest, DeleteUserTest)
- db.json — json-server için örnek veri
- postman_collection.json — Postman import dosyası
- .gitignore — önerilen ignore listesi

Local çalıştırma (önerilen akış)
1. automation dizinine geç:
   cd /Users/gulcancelik/Desktop/awsProject/aws-serverless-book-api-qa/automation

2. json-server ile mock API başlatın (ayrı terminal penceresi):
   npx json-server --watch db.json --port 3000

   veya global kurulum:
   npm install -g json-server
   json-server --watch db.json --port 3000

3. Testleri çalıştırın (başka bir terminalde):
   mvn clean test -Dapi.baseUrl=http://localhost:3000

   Tek bir test çalıştırmak için:
   mvn -Dtest=GetUserTest#getUserTest -Dapi.baseUrl=http://localhost:3000 test

Ortam değişkenleri / parametreler
- Sisteme göre base URL şu sırayla okunur:
  1. Maven system property: -Dapi.baseUrl=...
  2. Ortam değişkeni: API_BASE_URL
  3. Varsayılan: http://localhost:3000

Hata giderme
- UnknownHost: API host DNS çözülmüyor — gerçek endpoint verin veya local mock başlatın.
- Port 3000 kullanımda ise hangi sürecin dinlediğini bulun ve sonlandırın:
  lsof -iTCP:3000 -sTCP:LISTEN -n -P
  pkill -f json-server || true
- json-server / users yanıtlarında id string ise db.json içindeki id'yi sayısal yapın (ör. 1).

Postman
- Postman → Import → postman_collection.json
- Koleksiyondaki variable `baseUrl` değerini http://localhost:3000 yapın.

Git / GitHub
- Repo: https://github.com/gulcannce/my-aws-user-api-qa
- Commit & push:
  git add .
  git commit -m "Add tests, db.json and Postman collection"
  git push -u origin main

Notlar
- Debug için testlere eklenen log() satırlarını kaldırabilirsiniz (GetUserTest'te çıkarıldı).
- CI ortamında gerçek endpoint kullanacaksanız -Dapi.baseUrl ile uygun URL verin veya ortam değişkeni

# Robusta Challange

## Spring Boot

Excel için poi kütüphanesi, Api için CXF, ön yüz için swagger kullanılmıştır.

```sh
git clone https://github.com/cihangirgokoz/robustaChallenge.git
```
Projenin bulunduğu dizine gidelim
```sh
cd /PATH/robustaChallenge
```

Spring Tool Suit import edelim ve çalıştıralım.

Çalıştırma işlemini terminalden
```sh
mvn spring-boot:run 
```
Komutu ile de gerçekleştirebiliriz.

Postman üzerinde:

```sh
localhost:9898/services/excel
```
Endpointini kullanarak ilgili satır ve sütun datasını çekmek için örnek post body datası:
```json
{
  "action": "GETCELLDATA",
  "filePath": "EXCEL_DOSYA_PATH",
  "rowIndex": 5,
  "colIndex": 1
}
```
Respone:
```json
{
    "result": "success",
    "value": "yeni yazı deneme2"
}
```
```sh
localhost:9898/services/excel 
```
Endpointini kullanarak ilgili satır ve sütundaki datayı güncellemek için örnek post body datası:
```json
{
  "action": "SETCELLDATA",
  "filePath": "EXCEL_DOSYA_PATH",
  "rowIndex": 5,
  "colIndex": 1,
  "newValue" : "yeni yazı deneme3"
}
```
Response:
```json
{
    "result": "success",
    "value": "set new value  :yeni yazı deneme3 to row :5 cell :1"
}
```

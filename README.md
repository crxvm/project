# Учебный проект spring-boot

## Для запуска проекта понядобится 
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Запуск

Проект можно запустить через IDE

Так же можно использовать [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) 

```shell
mvn spring-boot:run
```

## Техническое задание 
Все описанные возвращаемые типы данных находятся в параметре data. В случае ошибки возвращается параметр error.

Например, в случае, если запрос корректно отработает, бэк возвращает в body ответа:
```json5
{
    "data":{
        //то, что в параметре out
    }
}
```


А в случае ошибки возвращает 
```json5
{
    "error":"текст ошибки"
}
```

Везде, где не написан метод, использовать POST

1. api/organization/list

In (фильтр):
```json5
{
  "name":"", //обязательный параметр
  "inn":"",
  "isActive":""
}
```
Out:
```json5
[
  {
    "id":"",
    "name":"",
    "isActive":"true"
  },
  ...
]
```

2. api/organization/{id}

method:GET

Out:
```json5
{
  "id":"",
  "name":"",
  "fullName":"",
  "inn":"",
  "kpp":"",
  "address":"",
  "phone":"",
  "isActive":"true"
}
```

3. api/organization/update

In:
```json5
{
  "id":"", //обязательный параметр
  "name":"", //обязательный параметр
  "fullName":"", //обязательный параметр
  "inn":"", //обязательный параметр
  "kpp":"",  //обязательный параметр
  "address":"", //обязательный параметр
  "phone":"",
  "isActive":"true"
}
```
Out:
```json5
{
    "result":"success"
}
```

4. api/organization/save

In:
```json5
{
  "name":"", //обязательный параметр
  "fullName":"", //обязательный параметр
  "inn":"", //обязательный параметр
  "kpp":"", //обязательный параметр
  "address":"", //обязательный параметр
  "phone":"",
  "isActive":"true"
}
```
Out:
```json5
{
    "result":"success"
}
```

5. api/office/list

In (фильтр):
```json5
{
  "orgId":"", //обязательный параметр
  "name":"",
  "phone":"",
  "isActive":"true"
}
```
Out:
```json5
[
  {
    "id":"",
    "name":"",
    "isActive":"true"
  },
  ...
]
```
6. api/office/{id}

method:GET

Out:
```json5
{
  "id":"",
  "name":"",
  "address":"",
  "phone":"",
  "isActive":"true"
}
```

7. api/office/update

In:
```json5
{
  "id":"", //обязательный параметр
  "name":"", //обязательный параметр
  "address":"", //обязательный параметр
  "phone":"",
  "isActive":"true" //пример
}
```
Out:
```json5
{
    "result":"success"
}
```


8. api/office/save

In:
```json5
{
  "orgId":"", //обязательный параметр
  "name":"",
  "address":"",
  "phone":"",
  "isActive":"true"
}
```

Out:
```json5
{
    "result":"success"
}
```


9. api/user/list

In (фильтр):
```json5
{
  "officeId":"", //обязательный параметр
  "firstName":"",
  "secondName":"",
  "middleName":"",
  "position": "",
  "docCode":"",
  "citizenshipCode":""
}
```

Out:
```json5
[
{
  "id":"",
  "firstName":"",
  "secondName":"",
  "middleName":"",
  "position":""
}
]
```

10. api/user/{id}

method:GET

Out:
```json5
{
  "id":"",
  "firstName":"",
  "secondName":"",
  "middleName":"",
  "position":"",
  "phone":"",
  "docName":"",
  "docNumber":"",
  "docDate":"",
  "citizenshipName":"",
  "citizenshipCode":"",
  "isIdentified":"true"
}
```


11. api/user/update

In:
```json5
{
  "id":"", //обязательный параметр
  "officeId":"",
  "firstName":"", //обязательный параметр
  "secondName":"",
  "middleName":"",
  "position":"", //обязательный параметр
  "phone":"",
  "docName":"",
  "docNumber":"",
  "docDate":"",
  "citizenshipCode":"",
  "isIdentified":"true" //пример
}
```

Out:
```json5
{
    "result":"success"
}
```

12. api/user/save

In:
```json5
{
  "officeId":"", //обязательный параметр
  "firstName":"", //обязательный параметр
  "secondName":"",
  "middleName":"",
  "position":"", //обязательный параметр
  "phone":"",
  "docCode":"",
  "docName":"",
  "docNumber":"",
  "docDate":"",
  "citizenshipCode":"",
  "isIdentified":"true" //пример
}
```

Справочники:

api/docs
```json5
[
  {
    "name":"Паспорт гражданина РФ",
    "code":"21"
  },
  ...
]
```

Виды документов, удостоверяющих личность физического лица

api/countries
```json5
[
  {
    "name":"Российская Федерация",
    "code":"643"
  },
  ...
]
```


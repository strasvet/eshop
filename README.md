# eshop
E-Shop project
For requests
Для начала регистрация, первый юзер в системе получает роль Админа
добавлять категории товаров может только Админ
все запросы Админ видит в полном виде в отличии от остальных

регистрация

post localhost:8080/users/register
{
	"firstName":"",
	"lastName":"",
	"email":"",
	"password":"",
	"userName":"",
	"balance":""
}

post localhost:8080/users/login
{
	"userName":"",
	"password":""
}
put localhost:8080/users/logout
only sessionID

get localhost:8080/users/all
only sessionID
admin и все остальные видят по разному

get localhost:8080/users/mail@outlook.com
only sessionID

добавление категорий только Админ + sessionID
post localhost:8080/category/add
{
	"name":"auto",
	"description":"category for Automobiles"
}
get localhost:8080/category/all
only sessionID

Добавление продукта - продавец не может купить сам у себя
post localhost:8080/product/register
{
	"displayName":"TOYOTA",
    "description":"COROLLA",
    "price":"1000",
    "category":1,
    "discount":1,
    "quantity":3
}
get localhost:8080/product/all 
only sessionID
admin и все остальные видят по разному

Покупка продукта
post localhost:8080/purchase/register
{
	"productId":3,
	"quantity":11
	
}
get localhost:8080/purchase/all
only sessionID
admin и все остальные видят по разному




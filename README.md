# catalog_ext
Catalog, list of products &amp; its details, except price.

Dependency
==========
myRetail has a dependency on myRetail - https://github.com/prasadbayi/my_retail

GET	http://localhost:8080/my_retail/products/{id}
================================================
Sample - http://localhost:8080/my_retail/products/15117733

GET	http://localhost:8082/catalog/products/{id}
==============================================
Sample - http://localhost:8082/catalog/products/15117729

GET	http://localhost:8080/pricing/products
==========================================
Sample - http://localhost:8080/pricing/products

GET	http://localhost:8080/pricing/products/{id}
==============================================
Sample - http://localhost:8080/pricing/products/15117730

POST	http://localhost:8080/pricing/add_product
==============================================
Sample - 
{
    "id":15117750,
    "value": 658.0,
    "currency_code": "USD"
}

PUT	http://localhost:8080/pricing/update_product/{id}
=====================================================
Sample - 
{
    "id":15118850,
    "value": 778.0,
    "currency_code": "USD"
}

DELETE	http://localhost:8080/pricing/delete_product/{id}
========================================================
Sample - http://localhost:8080/pricing/delete_product/15118850

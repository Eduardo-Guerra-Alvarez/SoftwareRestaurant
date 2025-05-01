#!/bin/bash
URL="http://localhost:8080/api"

echo "Creating employees..."
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John", "lastName":"Doe", "role":"WAITER", "password_hash":"1234", "isActive":true}'

curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Ana", "lastName":"Smith", "role":"CASHIER", "password_hash":"5678", "isActive":true}'

echo ""
echo "Creating tables..."
curl -X POST http://localhost:8080/api/tables \
  -H "Content-Type: application/json" \
  -d '{"table_number":1, "capacity":4, "isAvailable":true}'

curl -X POST http://localhost:8080/api/tables \
  -H "Content-Type: application/json" \
  -d '{"table_number":2, "capacity":6, "isAvailable":true}'

echo ""
echo "Creating menu items..."
curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Cheeseburger","description":"Beef burger with cheese","price":8.99,"category":"food","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Veggie Burger","description":"Burger with veggie patty","price":7.99,"category":"food","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"French Fries","description":"Crispy golden fries","price":3.50,"category":"food","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Coca Cola","description":"Chilled soda","price":1.99,"category":"drink","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Lemonade","description":"Fresh lemon juice","price":2.49,"category":"drink","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Chocolate Cake","description":"Moist chocolate dessert","price":4.99,"category":"dessert","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Ice Cream","description":"Vanilla ice cream scoop","price":2.99,"category":"dessert","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Grilled Chicken","description":"Chicken with herbs","price":9.49,"category":"food","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Orange Juice","description":"Fresh squeezed orange juice","price":2.79,"category":"drink","isActive":true}'

curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -d '{"name":"Apple Pie","description":"Warm apple pie slice","price":4.25,"category":"dessert","isActive":true}'

echo ""
echo "✅ Seeding completed! Now you can test order creation and order_details."

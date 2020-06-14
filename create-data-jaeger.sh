#!/bin/bash

items=(
"Jam:true"
"Bacon:false"
"Bread:true"
"Chicken:true"
"Butter:false"
"Milk:false"
"Tomato:true"
"Ice Cream:false"
"Bread:true"
"Steak:true"
"Mustard:false"
"Ketchup:false"
)


for item in "${items[@]}" ; do
  title="${item%%:*}"
  completed="${item##*:}"
  curl -X PUT -H "Content-Type: application/json" -d '{"title":"'$title'","'$completed'":"false"}' http://localhost:8080/todos | json_pp
  curl -X GET http://localhost:8080/todos | json_pp
done

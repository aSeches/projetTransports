# API Description

The following requests are available in projetTransports' API. 
All are directed to the Undertow utility _u_ = localhost:3000/.

### Citizen {URL c = "citizen"}

* **GET** *u/c/{id}* -> get a Citizen by its id
* **GET** *u/c* -> get all Citizens
* **POST** *u/c* -> create the Citizen with parameters specified in a JSON object

Body : 
```json
{
  "citizen": [
    {
      "name": "value",
      "home": "value",
      "work": "value"
    }
  ]
}
```
* **PUT** *u/c* -> modify a Citizen

Body : 
```json
{
  "citizen": [
    {
      "parameter1": "value",
      "parameterN": "value"
    }
  ]
}
```

* **DELETE** *u/c/{id} -> delete a Citizen by its id

### Bus {URL b = "bus"}

* **GET** *u/b/{id}* -> get a Bus by its id
* **GET** *u/b* -> get all Buses
* **POST** *u/b* -> create the Bus with parameters specified in a JSON object

Body : 
```json
{
  "bus": [
    {
      "passengers": "value",
      "name": "value"
    }
  ]
}
```
* **PUT** *u/b* -> modify a Bus

Body : 
```json
{
  "bus": [
    {
      "parameter1": "value",
      "parameterN": "value"
    }
  ]
}
```

* **DELETE** *u/b/{id} -> delete a Bus by its id

### Metro {URL m = "metro"}

* **GET** *u/m/{id}* -> get a Metro by its id
* **GET** *u/m* -> get all Metros
* **POST** *u/m* -> create the Metro with parameters specified in a JSON object

Body : 
```json
{
  "metro": [
    {
      "passengers": "value",
      "name": "value"
    }
  ]
}
```
* **PUT** *u/m* -> modify a Metro

Body : 
```json
{
  "metro": [
    {
      "parameter1": "value",
      "parameterN": "value"
    }
  ]
}
```

* **DELETE** *u/m/{id} -> delete a Metro by its id


### Velo {URL v = "velo"}

* **GET** *u/v/{id}* -> get a Velo by its id
* **GET** *u/v* -> get all Velo
* **POST** *u/v* -> create the Velo with parameters specified in a JSON object

Body : 
```json
{
  "velo": [
    {
      "passengers": "value",
      "name": "value"
    }
  ]
}
```
* **PUT** *u/v* -> modify a Velo

Body : 
```json
{
  "velo": [
    {
      "parameter1": "value",
      "parameterN": "value"
    }
  ]
}
```

* **DELETE** *u/v/{id} -> delete a Velo by its id

### Journey {URL j = "journey"}

* **GET** *u/j/{id}* -> get a Journey by its id
* **GET** *u/j* -> get all Journeys
* **POST** *u/j* -> create the Journey with parameters specified in a JSON object

Body : 
```json
{
  "journey": [
    {
      "departure": "value",
      "arrival": "value",
      "commute": "value"
    }
  ]
}
```
* **PUT** *u/j* -> modify a journey

Body : 
```json
{
  "journey": [
    {
      "parameter1": "value",
      "parameterN": "value"
    }
  ]
}
```

* **DELETE** *u/j/{id} -> delete a journey by its id

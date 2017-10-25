
//Libraries
#include <DHT.h>

//Constants
#define DHTPIN A0     // what pin we're connecPted to
#define DHTTYPE DHT22   // DHT 22  (AM2302)

// Initialize DHT sensor for normal 16mhz Arduino
DHT dht(DHTPIN, DHTTYPE);

//Variables
float hum;  //Stores humidity value
float temp; //Stores temperature value
int ledPin = 13;
int value_received = 121;


void setup(){
    Serial.begin(9600);
    dht.begin();
    pinMode(ledPin, OUTPUT);
}

void loop(){
  //DHT
    //Read data and store it to variables hum and temp
    float hum = dht.readHumidity();
    float temp= dht.readTemperature();
    
    //Print temp and humidity values to serial monitor with " 25.64;50.20" Format
    /*
    Serial.print("Humidité: ");
    Serial.print(hum);
    Serial.print(" %, Température: ");
    Serial.print(temp);
    Serial.println(" °C");
    delay(500); //Delay 2 sec.
    Serial.print("DATA;");*/
    Serial.print(temp);
    Serial.print(";");
    Serial.print(hum);
    Serial.println(";");

    // if serial port is available, read incoming bytes
  if (Serial.available() > 0) {
    
    value_received = Serial.read();
    
      digitalWrite(ledPin, value_received);
      
    Serial.print("Valeur reçue: ");
    Serial.println(value_received);
  }

    

    delay(1000); //Delay 1 sec.*/
}

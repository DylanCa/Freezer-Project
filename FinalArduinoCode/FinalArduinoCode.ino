
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
int value_received;
int ThermistorPin = 4;
int Vo;
float R1 = 10000;
float logR2, R2, T;
float c1 = 1.009249522e-03, c2 = 2.378405444e-04, c3 = 2.019202697e-07;



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

     Vo = analogRead(ThermistorPin);
  R2 = R1 * (1023.0 / (float)Vo - 1.0);
  logR2 = log(R2);
  T = (1.0 / (c1 + c2*logR2 + c3*logR2*logR2*logR2));
  T = T - 273.15;
  //T = (T * 9.0)/ 5.0 + 32.0; 
    
    //Print temp and humidity values to serial monitor with " 25.64;50.20" Format
  
    Serial.print(temp);
    Serial.print(";");
    Serial.print(hum);
    Serial.print(";");
    Serial.print(T);
    Serial.println(";");

    // if serial port is available, read incoming bytes
    if (Serial.available() > 0) {
      
      value_received = Serial.read();
      
        digitalWrite(ledPin, value_received);
    }
    
    delay(1000); //Delay 1 sec.*/
}

#include <SoftwareSerial.h>
#define RX_PIN 10
#define TX_PIN 11

SoftwareSerial btSerial = SoftwareSerial(RX_PIN, TX_PIN);

void setup() {

  pinMode(TX_PIN, OUTPUT);
  pinMode(RX_PIN, INPUT);

  btSerial.begin(9600);
  Serial.begin(9600);
  Serial.println("Received via bluetooth:\n");
}

void loop() {
  
  while (Serial.available()) 
    btSerial.write(Serial.read()); // sent
  
  while (btSerial.available()) 
     Serial.write(btSerial.read()); // received
}

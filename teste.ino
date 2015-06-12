


#define INTERVALOENTRELEITURAS 20 // milisegundos (ms)
#define MAXIMOPROBLEMAS        10 // tentativas
#define CICLORESPIRATORIO      4500 // milisegundos ou 4.5s
#define DEBUG 1
#define LIMITERESPIRACAO       20

unsigned long time, soma, resp, air;
int c, flag;
byte key;
int led =13;

void setup() {
  /* Inicia valores de retorno e controle*/
  resp = 0;
  air  = 0;
  c    = 250;

  pinMode(led, OUTPUT); 

  Serial.begin(115200);
  time = millis();

  /*
   Serial.println("Digite o numero para buscar a informacao:");
   Serial.println("1 - Media de valores somados durante o fluxo respiratorio.");
   Serial.println("2 - Valor no momento atual.");
   Serial.println("3 - Verificar anormalidade no aparelho ou fluxo respiratorio.");
   */
}

void loop() {
  if(Serial.available() > 0){//verifica se a serial recebeu valores


    for(resp = 0; resp < 3; resp++){   
      digitalWrite(led, LOW);   // turn the LED on (HIGH is the voltage level)
      delay(500);               // wait for a second
      digitalWrite(led, HIGH);
      delay(500);
    }

    key = Serial.read();
    key = key - '0';
    Serial.println(key);
    Serial.flush();
    if(key == 1){
      c++;
      EnviaSerial(c);
      delay(1000);
    }
  }
}

void EnviaSerial(int resp){
  byte h, l;
  h = highByte(resp);
  l = lowByte(resp);

  Serial.println(h);
  delay(100);
  Serial.println(l);
  Serial.flush();
}




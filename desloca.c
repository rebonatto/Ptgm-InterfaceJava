#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>

#define N 256
#define H 12

//float vet[N*2]={20.57,21.57,20.57,23.57,23.57,23.57,23.57,23.57,24.57,25.57,26.57,26.57,27.57,27.57,27.57,27.57,27.57,27.57,27.57,27.57,27.57,27.57,27.57,27.57,27.57,28.57,27.57,26.57,27.57,27.57,28.57,27.57,27.57,27.57,27.57,27.57,29.57,29.57,27.57,26.57,26.57,26.57,24.57,24.57,25.57,24.57,26.57,23.57,23.57,22.57,22.57,22.57,21.57,21.57,21.57,20.57,21.57,19.57,19.57,17.57,18.57,17.57,16.57,15.57,14.57,14.57,14.57,13.57,13.57,12.57,11.57,11.57,10.57,10.57,10.57,8.57,7.57,7.57,6.57,5.57,5.57,-0.43,4.57,3.57,2.57,1.57,0.57,0.57,-1.43,-1.43,-1.43,-3.43,-4.43,-4.43,-5.43,-9.43,-7.43,-7.43,-8.43,-9.43,-10.43,-10.43,-12.43,-11.43,-13.43,-13.43,-14.43,-15.43,-14.43,-14.43,-16.43,-17.43,-17.43,-19.43,-18.43,-19.43,-20.43,-20.43,-21.43,-22.43,-21.43,-22.43,-23.43,-23.43,-23.43,-24.43,-24.43,-25.43,-24.43,-26.43,-26.43,-27.43,-27.43,-27.43,-27.43,-28.43,-28.43,-28.43,-29.43,-24.43,-29.43,-29.43,-29.43,-29.43,-29.43,-28.43,-29.43,-29.43,-28.43,-28.43,-28.43,-28.43,-28.43,-28.43,-29.43,-28.43,-28.43,-28.43,-28.43,-28.43,-27.43,-28.43,-27.43,-28.43,-26.43,-26.43,-25.43,-26.43,-25.43,-24.43,-24.43,-25.43,-24.43,-23.43,-23.43,-22.43,-22.43,-22.43,-20.43,-20.43,-20.43,-19.43,-19.43,-18.43,-16.43,-16.43,-16.43,-16.43,-15.43,-14.43,-14.43,-13.43,-12.43,-12.43,-12.43,-11.43,-9.43,-9.43,-9.43,-8.43,-8.43,-7.43,-6.43,-5.43,-5.43,-4.43,-4.43,-2.43,-1.43,-1.43,-0.43,0.57,-0.43,1.57,3.57,3.57,3.57,4.57,4.57,5.57,7.57,7.57,8.57,9.57,9.57,9.57,11.57,11.57,7.57,12.57,13.57,12.57,13.57,15.57,15.57,16.57,17.57,18.57,18.57,18.57,19.57,19.57,20.57,20.57,21.57,22.57,22.57,23.57,23.57,24.57,24.57,23.57,25.57,25.57,27.57,26.57};


float vet[N*2]={2113.00,2114.00,2113.00,2116.00,2116.00,2116.00,2116.00,2116.00,2117.00,2118.00,2119.00,2119.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2120.00,2121.00,2120.00,2119.00,2120.00,2120.00,2121.00,2120.00,2120.00,2120.00,2120.00,2120.00,2122.00,2122.00,2120.00,2119.00,2119.00,2119.00,2117.00,2117.00,2118.00,2117.00,2119.00,2116.00,2116.00,2115.00,2115.00,2115.00,2114.00,2114.00,2114.00,2113.00,2114.00,2112.00,2112.00,2110.00,2111.00,2110.00,2109.00,2108.00,2107.00,2107.00,2107.00,2106.00,2106.00,2105.00,2104.00,2104.00,2103.00,2103.00,2103.00,2101.00,2100.00,2100.00,2099.00,2098.00,2098.00,2092.00,2097.00,2096.00,2095.00,2094.00,2093.00,2093.00,2091.00,2091.00,2091.00,2089.00,2088.00,2088.00,2087.00,2083.00,2085.00,2085.00,2084.00,2083.00,2082.00,2082.00,2080.00,2081.00,2079.00,2079.00,2078.00,2077.00,2078.00,2078.00,2076.00,2075.00,2075.00,2073.00,2074.00,2073.00,2072.00,2072.00,2071.00,2070.00,2071.00,2070.00,2069.00,2069.00,2069.00,2068.00,2068.00,2067.00,2068.00,2066.00,2066.00,2065.00,2065.00,2065.00,2065.00,2064.00,2064.00,2064.00,2063.00,2068.00,2063.00,2063.00,2063.00,2063.00,2063.00,2064.00,2063.00,2063.00,2064.00,2064.00,2064.00,2064.00,2064.00,2064.00,2063.00,2064.00,2064.00,2064.00,2064.00,2064.00,2065.00,2064.00,2065.00,2064.00,2066.00,2066.00,2067.00,2066.00,2067.00,2068.00,2068.00,2067.00,2068.00,2069.00,2069.00,2070.00,2070.00,2070.00,2072.00,2072.00,2072.00,2073.00,2073.00,2074.00,2076.00,2076.00,2076.00,2076.00,2077.00,2078.00,2078.00,2079.00,2080.00,2080.00,2080.00,2081.00,2083.00,2083.00,2083.00,2084.00,2084.00,2085.00,2086.00,2087.00,2087.00,2088.00,2088.00,2090.00,2091.00,2091.00,2092.00,2093.00,2092.00,2094.00,2096.00,2096.00,2096.00,2097.00,2097.00,2098.00,2100.00,2100.00,2101.00,2102.00,2102.00,2102.00,2104.00,2104.00,2100.00,2105.00,2106.00,2105.00,2106.00,2108.00,2108.00,2109.00,2110.00,2111.00,2111.00,2111.00,2112.00,2112.00,2113.00,2113.00,2114.00,2115.00,2115.00,2116.00,2116.00,2117.00,2117.00,2116.00,2118.00,2118.00,2120.00,2119.00};


void deslocaOriginal(float * vector);
void deslocaProposto(float * data);
void mostravet(float * vet);

int main(){
    printf("Original\n");
    mostravet(vet);
    //deslocaOriginal(vet);
    deslocaProposto(vet);
    printf("Meu\n");
    mostravet(vet);

}

void deslocaProposto(float * data){
    int met, n;

    met=N-1;    
    for(n=N*2-1; n > 0; n--){
        if (n % 2 == 0){
            data[n] = data[met];
            met--;
        }
        else
            data[n] = 0;
    } 
}

void deslocaOriginal(float * vector){
    int n;
    float seila[N*2];
    for(n=0; n<N;n++)
    {
        if(n<N){
            //vector[2*n]= (float) ( (data[n] - Settings::get_Offset(ch)) / Settings::get_Gain(ch) );            
            seila[2*n]= (float) vector[n] ;            
            //  printf("%.4f$", vector[2*n]);
            }
        else
            seila[2*n]=0;
        seila[2*n+1]=0;
    }   
    printf("Deslocado\n");
    mostravet(seila);    
}

void mostravet(float * vet){
    int i;
    
    for(i=0; i < N*2; i++)
        printf("%.2f ", vet[i]);
    printf("\n");
}


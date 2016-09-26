using System;

namespace prueba
{
	public class Tablero {



		private char [,] mTablero;
		private int filas;
		private int columnas;
		private const char VIVA = '*';
		private const char MUERTA = ' ';
		Random rnd = new Random();


		public char[,] getmTablero() {
			return mTablero;
		}

		public Tablero(int filas, int columnas) {
			this.filas = filas;
			this.columnas = columnas;
			mTablero = new char[filas, columnas];
			cargarTablero();
		}

		private void cargarTablero(){


			int valor = 0;

			for (int i = 0; i < mTablero.GetLength(0); i++){

				for(int j = 0; j < mTablero.GetLength(1); j++){

					valor = rnd.Next (2);

					if(valor == 1){

						mTablero[i,j] = VIVA;

					}else{

						mTablero[i,j] = MUERTA;
					}
				}

			}


		}

		public void avanzarGeneracion(){


			char[,] copia = new char[filas, columnas];


			for (int i = 0; i < mTablero.GetLength(0); i++){

				for(int j = 0; j < mTablero.GetLength(1); j++){


					copia[i,j] = comprobarVida(i,j, mTablero);

				}

			}

			mTablero = copia;

		}

		private char comprobarVida(int i, int j, char[,] mTablero) {

			char[,] subMatriz = new char[3,3];
			int nVivas = 0;
			char celula = ' ';
			char estado = ' ';


			subMatriz[0,0] = mTablero[((i-1) + mTablero.GetLength(0)) % mTablero.GetLength(0), ((j-1) + mTablero.GetLength(1)) % mTablero.GetLength(1)];
			subMatriz[0,1] = mTablero[((i-1) + mTablero.GetLength(0)) % mTablero.GetLength(0), (j + mTablero.GetLength(1)) % mTablero.GetLength(1)];
			subMatriz[0,2] = mTablero[((i-1) + mTablero.GetLength(0)) % mTablero.GetLength(0), (j +1) % mTablero.GetLength(1)];

			subMatriz[1,0] = mTablero[(i + mTablero.GetLength(0)) % mTablero.GetLength(0) , ((j-1 + mTablero.GetLength(1))) % mTablero.GetLength(1)];
			subMatriz[1,1] = mTablero[(i + mTablero.GetLength(0)) % mTablero.GetLength(0), j];
			subMatriz[1,2] = mTablero[(i+1)%mTablero.GetLength(0), (j+1) % mTablero.GetLength(1)];

			subMatriz[2,0] = mTablero[( i +1 + mTablero.GetLength(0)) % mTablero.GetLength(0), ((j-1) + mTablero.GetLength(1)) % mTablero.GetLength(1)];
			subMatriz[2, 1] = mTablero[( i +1 + mTablero.GetLength(0)) % mTablero.GetLength(0), j];
			subMatriz[2, 2] = mTablero[( i +1 + mTablero.GetLength(0)) % mTablero.GetLength(0), ((j+1) % mTablero.GetLength(1))];



			for (int f = 0; f < subMatriz.GetLength(0); f++){

				for (int c = 0; c < subMatriz.GetLength(1); c++){

					if(c == 1 && f == 1){

						celula = subMatriz[f,c];

					}else{

						if(subMatriz[f, c] == VIVA){

							nVivas++;
						}

					}

				}

			}

			if(celula == VIVA && nVivas > 3){

				estado = MUERTA;

			}else if(celula == MUERTA && nVivas == 3){

				estado = VIVA;

			}else if(celula == VIVA && (nVivas == 2 || nVivas == 3)){

				estado = MUERTA;
			}

			return estado;

		}


	}
}

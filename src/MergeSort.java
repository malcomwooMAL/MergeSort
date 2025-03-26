// Merge sort in Java

// Merge sort em Java

import java.util.Random;

class MergeSort {

  /**
   * Mescla (combina) dois subarrays ordenados L e M de volta no array arr.
   * O primeiro subarray é arr[p..q]
   * O segundo subarray é arr[q+1..r]
   * Pré-condição: Assume-se que arr[p..q] e arr[q+1..r] já estão ordenados.
   *
   * @param vetor O array original que contém os subarrays a serem mesclados.
   * @param indiceInicialL   O índice inicial do primeiro subarray.
   * @param indiceFinalL   O índice final do primeiro subarray.
   * @param indiceFinalR   O índice final do segundo subarray.
   */
  void merge(int vetor[], int indiceInicialL, int indiceFinalL, int indiceFinalR) {

      // --- 1. Preparação ---

      // Calcula o tamanho do primeiro subarray (arr[p..q])
      int vetorLTamanho = indiceFinalL - indiceInicialL + 1;
      // Calcula o tamanho do segundo subarray (arr[q+1..r])
      int vetorRTamanho = indiceFinalR - indiceFinalL;

      // Cria arrays temporários para armazenar os elementos dos subarrays
      // Precisamos deles porque vamos sobrescrever arr[p..r] durante a mesclagem
      int L[] = new int[vetorLTamanho]; // Array temporário para a parte esquerda (Left)
      int M[] = new int[vetorRTamanho]; // Array temporário para a parte direita (Middle/Right)

      // Copia os dados dos subarrays originais para os arrays temporários
      for (int i = 0; i < vetorLTamanho; i++)
          L[i] = vetor[indiceInicialL + i]; // Copia de arr[p] até arr[q] para L[]
      for (int j = 0; j < vetorRTamanho; j++)
          M[j] = vetor[indiceFinalL + 1 + j]; // Copia de arr[q+1] até arr[r] para M[]

      // --- 2. Mesclagem (Merge) ---

      // Mantém os índices atuais para percorrer os subarrays temporários (L e M)
      // e o índice para o array principal (arr) onde a mesclagem será colocada.
      int i, j, k;
      i = 0; // Índice inicial para o subarray L
      j = 0; // Índice inicial para o subarray M
      k = indiceInicialL; // Índice inicial para o subarray mesclado em arr[p..r]

      // O loop principal de mesclagem:
      // Continua enquanto houver elementos em *ambos* os arrays L e M para comparar.
      while (i < vetorLTamanho && j < vetorRTamanho) {
          // Compara o elemento atual de L com o elemento atual de M
          if (L[i] <= M[j]) {
              // Se o elemento de L for menor ou igual, ele é o próximo na ordem.
              // Coloca L[i] na posição correta (k) do array original arr.
              vetor[k] = L[i];
              // Avança para o próximo elemento no array L.
              i++;
          } else {
              // Se o elemento de M for menor, ele é o próximo na ordem.
              // Coloca M[j] na posição correta (k) do array original arr.
              vetor[k] = M[j];
              // Avança para o próximo elemento no array M.
              j++;
          }
          // Em ambos os casos, avança para a próxima posição no array original arr
          // onde o próximo elemento ordenado será colocado.
          k++;
      }

      // --- 3. Cópia dos Elementos Restantes ---

      // Após o loop principal, um dos arrays temporários (L ou M) pode ter
      // elementos restantes, pois o outro foi totalmente consumido.
      // Copia os elementos restantes de L[], se houver algum.
      while (i < vetorLTamanho) {
          vetor[k] = L[i];
          i++;
          k++;
      }

      // Copia os elementos restantes de M[], se houver algum.
      // Apenas *um* desses dois loops while (o de cima ou este) será executado.
      while (j < vetorRTamanho) {
          vetor[k] = M[j];
          j++;
          k++;
      }
  } // Fim da função merge

  /**
   * Função principal que implementa o Merge Sort usando a abordagem "Dividir para Conquistar".
   * Divide o array recursivamente em duas metades, ordena-as e depois as mescla.
   *
   * @param arr O array a ser ordenado.
   * @param indiceInicialL   O índice inicial (left) do segmento do array a ser considerado.
   * @param indiceFinalR   O índice final (right) do segmento do array a ser considerado.
   */
  void mergeSort(int vetor[], int indiceInicialL, int indiceFinalR) {
      // Condição de parada da recursão (caso base):
      // Se indiceInicialL >= indiceFinalR, o segmento tem 0 ou 1 elemento, que já está ordenado por definição.
      if (indiceInicialL < indiceFinalR) {

          // --- 1. Dividir ---
          // Encontra o ponto médio para dividir o array em duas metades:
          // Subarray esquerdo: arr[l...m]
          // Subarray direito: arr[m+1...r]
          int pontoMedio = (indiceInicialL + indiceFinalR) / 2;

          // --- 2. Conquistar ---
          // Chama recursivamente mergeSort para ordenar a primeira metade.
          mergeSort(vetor, indiceInicialL, pontoMedio);
          // Chama recursivamente mergeSort para ordenar a segunda metade.
          mergeSort(vetor, pontoMedio + 1, indiceFinalR);

          // --- 3. Combinar ---
          // Quando as duas metades [l..m] e [m+1..r] estiverem ordenadas
          // (devido ao retorno das chamadas recursivas), mescla-as.
          merge(vetor, indiceInicialL, pontoMedio, indiceFinalR);
      }
  } // Fim da função mergeSort

  /**
   * Função utilitária para imprimir o array.
   * @param arr O array a ser impresso.
   */
  static void printArray(int arr[]) {
      int n = arr.length;
      for (int i = 0; i < n; ++i)
          System.out.print(arr[i] + " ");
      System.out.println();
  }

  static int [] randomArray(int [] array){

    Random random = new Random();

    for (int i = 0; i < array.length; i++){
        array[i] = random.nextInt(0,10001);
    }

    return array;

  }

  // Programa principal (Driver program) para testar o Merge Sort
  public static void main(String args[]) {

       // Instanciar um Array
      int [] arr = new int[100000000];
      
      // preencher o vetor com valores inteiros aleatórios entre 0 e 1000
      arr = randomArray(arr);

      //exibir o array original
      //System.out.println("Array original:");
      //printArray(arr);

      // Cria um objeto da classe MergeSort
      MergeSort ob = new MergeSort();
      // Chama o método mergeSort para ordenar o array completo
      // Começa do índice 0 até o último índice (arr.length - 1)
      
      long start = System.currentTimeMillis();
      ob.mergeSort(arr, 0, arr.length - 1);
      long elapse = System.currentTimeMillis() - start;
      
      System.out.println(elapse);
      // 100 elementos no vetor - 0 milissegundos
      // 1000 elementos no vetor - 1 milissegundos
      // 10000 elementos no vetor -  5 milissegundos
      // 100000 elementos no vetor - 37 milissegundos
      // 1000000 - 286 milissegundos
      // 10000000 - 2201 milissegundos
      // 100000000 - 14833 milissegundos

      //System.out.println("\nArray ordenado:");
      //printArray(arr);
  }
}


function calcularPiso(){
  document.form3.resultadoArea3.value = (alturaPi.value * larguraPi.value)+ "m²";
  document.form3.resultadoAreia3.value = (alturaPi.value * larguraPi.value) * 2+ "Kg";

  if (form3.espessuraPiso.selectedIndex == 0) {
    document.form3.result.resultadoCimento3.value = "Selecione a espessura do reboco para o cálculo ser feito!"
  }
  if (form3.espessuraPiso.selectedIndex == 1) {
    document.form3.resultadoCimento3.value = (alturaPi.value * larguraPi.value) *6 + " Kg";
  }else if (form3.espessuraPiso.selectedIndex == 2) {
    document.form3.resultadoCimento3.value = (alturaPi.value * larguraPi.value) *8 + " Kg";
  }

}
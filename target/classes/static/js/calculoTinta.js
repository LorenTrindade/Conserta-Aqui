
function calcularTinta(){
  document.form4.resultadoArea4.value = (alturaTinta.value * larguraTinta.value)+ "m²";
  document.form4.superficie.value = tipoSuperficie.value;
  document.form4.resultadoTinta.value = parseFloat((alturaTinta.value * larguraTinta.value * 0.1 * qtDemao.value)) + "L";

}

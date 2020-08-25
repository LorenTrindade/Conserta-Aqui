function conversaoTinta(){
  document.form4.totalTinta.value = parseFloat((alturaTinta.value * larguraTinta.value * 0.1 * qtDemao.value)) + "L";


  if (form4.tipoLata.selectedIndex == 0) {
    document.form4.converter.value = "Selecione um tipo de tijolo para o cálculo ser feito!"
  }
  if (form4.tipoLata.selectedIndex == 1) {
    document.form4.converter.value = ((alturaTinta.value * larguraTinta.value * 0.1 * qtDemao.value)/18) + " Lata(s)";
  }else if (form4.tipoLata.selectedIndex == 2) {
   document.form4.converter.value = ((alturaTinta.value * larguraTinta.value * 0.1 * qtDemao.value)/3.6) + " Lata(s)";
 }else if (form4.tipoLata.selectedIndex == 3) {
   document.form4.converter.value = (Math.round((alturaTinta.value * larguraTinta.value * 0.1 * qtDemao.value)/0.9) / 1) + " Lata(s)";
 }

}

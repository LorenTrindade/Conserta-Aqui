
    function calcularParede(){
      document.form.resultadoArea.value = (alturaPa.value * larguraPa.value)+ "m²";
      document.form.resultadoCimento.value = (alturaPa.value * larguraPa.value) *6 + "Kg";
      document.form.resultadoAreia.value = (alturaPa.value * larguraPa.value) * 2 + "Kg";

      if (form.tipoTijolo.selectedIndex == 0) {
        document.form.result.texto.value = "Selecione um tipo de tijolo para o cálculo ser feito!"
      }
      if (form.tipoTijolo.selectedIndex == 1) {
        document.form.texto.value = (alturaPa.value * larguraPa.value) *34 + " Unidades";
      }else if (form.tipoTijolo.selectedIndex == 2) {
        document.form.texto.value = (alturaPa.value * larguraPa.value) *28 + " Unidades";
      }else if (form.tipoTijolo.selectedIndex == 3) {
        document.form.texto.value = (alturaPa.value * larguraPa.value) *28 + " Unidades";
      }

      if (form.tipoTijolo.selectedIndex == 0) {
        document.form.result.resultadoCal.value = "Selecione um tipo de tijolo para o cálculo ser feito!"
      }
      if (form.tipoTijolo.selectedIndex == 1) {
        document.form.resultadoCal.value = (alturaPa.value * larguraPa.value) *4 + "Kg";
      }else if (form.tipoTijolo.selectedIndex == 2) {
        document.form.resultadoCal.value = (alturaPa.value * larguraPa.value) *3 + "Kg";
      }else if (form.tipoTijolo.selectedIndex == 3) {
        document.form.resultadoCal.value = (alturaPa.value * larguraPa.value) *3 + "Kg";
      }
    }

  
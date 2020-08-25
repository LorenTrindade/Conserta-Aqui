
        function calcularReboco(){
          document.form2.resultadoArea2.value = (alturaRe.value * larguraRe.value)+ "m²";
          document.form2.resultadoAreia2.value = (alturaRe.value * larguraRe.value) * 2+ "Kg";

          if (form2.espessura.selectedIndex == 0) {
            document.form2.result.resultadoCimento2.value = "Selecione a espessura do reboco para o cálculo ser feito!"
          }
          if (form2.espessura.selectedIndex == 1) {
            document.form2.resultadoCimento2.value = (alturaRe.value * larguraRe.value) *7 + " Kg";
          }else if (form2.espessura.selectedIndex == 2) {
            document.form2.resultadoCimento2.value = (alturaRe.value * larguraRe.value) *12 + " Kg";
          }


          if (form2.espessura.selectedIndex == 0) {
            document.form2.resultadoCal2.value = "Selecione a espessura do reboco para o cálculo ser feito!"
          }
          if (form2.espessura.selectedIndex == 1) {
            document.form2.resultadoCal2.value = (alturaRe.value * larguraRe.value) *4 + "Kg";
          }else if (form2.espessura.selectedIndex == 2) {
            document.form2.resultadoCal2.value = (alturaRe.value * larguraRe.value) *5 + "Kg";
          }
        }

      
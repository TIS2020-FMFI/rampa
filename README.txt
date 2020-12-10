milkrun schedule

Navod ako si otvorit projekt v Intellij:

1) urobit git clone z githubu
2) vytvoreny priecinok si otvorit v IntelliJ
3) stiahnut si javaFx sdk 11 a zapamatat si niekde, kde si ho ulozil
4) pridat do Libraries JavaFxSDK
  - chod do File>Project Structure> Libraries.
  - stlac plusko, Java, a chod do priecinku kde si stiahol javafx sdk 11 a vyber priecinok "lib"
  - daj Apply a zavri to okno

  - Potom stlac v pravo hore, ako je napisane "Main", tam kde je tlacitko Run, tak vedla neho klikni na taku sipocku smerom dole,
    tam vyber Edit Configurations a tam do VM options prekopiruj (NEZABUDNI VSAK ZMENIT module-path na cestu, kde sa ti nachadza
    lib priecinok zo stiahnuteho javafx sdk 11..ja mam "C:\Users\ferok\IdeaProjects\Java\javafx-sdk-11.0.2\lib" ty mozes mas inu
    tak si ju zmen):

    --module-path "C:\Users\ferok\IdeaProjects\Java\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls,javafx.fxml

5) potom pridaj do libraries aj kniznice pre apache POI = API ktore vytvara Excel subory
   - chod do File>Project Structure> Libraries.
   - stlac plusko, Java 
   - a chod do priecinku, kde je tento projekt rampy ulozeny, otvor priecinok lib a oznac vsetky .jar subory (priecinok javafx-sdk-lib NIE)
   - daj Apply a vsetko by malo fungovat
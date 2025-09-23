public class InformationChecker {

    /*
    Enkel Enhet for å kontrollere en alter. Hvis alderen er innenfor akseptabel rekkefølge (0-120), returneres alderen
    slik den var. Hvis input-alderen er utenfor akseptabel rekkevidde, transformeres  alderen til nærmeste aksepterte
    alderen.
     */
    public int controlAge(int age) {
        if (age > 120) {
            return 120;
        }
        else if (age < 0) {
            return 0;
        }
        return age;
    }

}

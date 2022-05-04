package com.example.dailytrackerapp.ui;

import java.util.ArrayList;

/**
 * This Contents class gives the contents for the first list activity and description of the clicked number on the second activity
 * @author Silja
 */

public class Contents {
    private static final Contents instance = new Contents();
    private ArrayList<Number> numbers;

    private Contents() {
        numbers = new ArrayList<>();
        numbers.add(new Number("112 Hätänumero, Emergency number", "112 Hätänumeroon tulee soittaa ainoastaan kiireellisissä, todellisissa hätätilanteissa, hengen, terveyden, omaisuuden tai ympäristön ollessa uhattuna tai vaarassa tai, jos on syytä epäillä olevan.\n\n122 The emergency number should only be called in urgent, real emergencies, where there is a threat or danger to life, health, property or the environment, or if there is reason to suspect that."));
        numbers.add(new Number("116117 Päivystysapu, Emergency assistance", "116117 Päivystysapu on sairaanhoitopiirien järjestämä neuvonta- ja ohjauspalvelu, josta voi kysyä neuvoja äkillisiin sosiaalisiin tai terveydellisiin ongelmiin. Numeroa voi käyttää esimerkiksi silloin, kun oma terveysasema on kiinni ja pohtii päivystykseen lähtöä. Tarkoitettu kiirellisiin, ei-hätätilanteisiin, erityisesti päivystysaikana.\n\n116117 Emergency care is a counseling and guidance service provided by hospital districts for advice on sudden social or health problems. The number can be used, for example, when your own health center is closed and you are considering going to the emergency room. Intended for urgent, non-emergency situations, especially during on-call time."));
        numbers.add(new Number("010195202 Kriisipuhelin, Crisis phone", "010195202 Mielenterveysseuran ilmainen valtakunnallinen kriisipuhelin. Aukioloajat: ma-pe klo 09.00-07.00, la-su klo 15.00-07.00.\n\n010195202 Mental Health Association's free nationwide crisis phone. Opening hours: Mon-Fri 09.00-07.00, Sat-Sun 15.00-07.00."));
        numbers.add(new Number("0925250111 Kriisipuhelin, Crisis phone", "0925250111 MIELI ry :n Kriisipuhelin, joka päivystää 24 h vuorokaudessa joka päivä. Voit soittaa nimettömästi ja luottamuksellisesti. Ruotsinkielinen linja on 0925250112, kyseisestä numerosta saa apua myös englanniksi. Ruotsinkielisen linjan palveluajat ovat ma, ke klo 16.00-20.00 ja ti, to, pe klo 09.00-13.00.\n\n0925250111 MIELI ry's Crisis telephone, which is on duty 24 hours a day, every day. You can call anonymously and confidentially. The Swedish language line is 0925250112, this number provides help in English also. The service hours for the Swedish-language line are Mon, Wed 16.00-20.00 and Tue, Thu, Fri from 09.00-13.00."));
        numbers.add(new Number("020391920 Mielenterveysneuvonta, Mental health counseling", "020391920 Valtakunnallinen mielenterveysneuvonta, arkisin klo 10.00-15.00.\n\n020391920 Nationwide mental health counseling, weekdays from 10 a.m. to 3 p.m."));
        numbers.add(new Number("116111 Lasten ja nuorten puhelin, Telephone for children and young people", "116111 Lasten ja nuorten puhelin, joka palvelee ma-pe klo 14.00-20.00 ja la-su klo 17.00-20.00 vuoden jokaisena päivänä. Puhelu on täysin ilmainen ja sen voi tehdä nimettömästi.\n\n116111 Telephone for children and young people, open Mon-Fri 14.00-20.00 and Sat-Sun 17.00-20.00 every day of the year. The call is completely free and can be made anonymously."));
    }

    public static Contents getInstance() {
        return instance; }

        public ArrayList<Number> getNumbers() { return numbers; }

        public Number getNumber(int i) { return numbers.get(i); }
}
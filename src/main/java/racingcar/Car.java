package racingcar;
import camp.nextstep.edu.missionutils.Randoms;
public class Car {

    private final String name;
    private int position = 0;



    public Car(String name) {
        this.name = name;
    }
    // 추가 기능 구현


    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    //자동차 이동
    public void move(){
        position+=decideMove();
    }

    //자동차 이동 결정
    public int decideMove(){

        int randomValue=Randoms.pickNumberInRange(0,9);

        if (randomValue>=4){
            return 1; //랜덤값이 4이상이면 한칸 움직인다.
        }

        return 0;//랜덤값이 3이하이면 정지한다.
    }

}

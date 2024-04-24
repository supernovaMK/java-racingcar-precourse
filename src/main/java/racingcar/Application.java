package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행

        prepareRacing();

        playRacing();

        findWinner();

    }
    private static List<Car> cars;
    private static int attempts;
    private static int maxScore=0;   //최고 점수

    //경기 준비
    private static void prepareRacing(){
        getCarNamesFromUser();

        getAttemptsFromUser();

        System.out.println();
    }

    public static void getCarNamesFromUser(){
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

        String[] names = Console.readLine().split(",");//경기에 참가할 선수(들) 이름 입력


        cars = createCars(names);//자동차 생성

    }

    public static void getAttemptsFromUser(){
        try {
            System.out.println("시도할 회수는 몇회인가요?");
            attempts = Integer.parseInt(Console.readLine());//시도할 횟수 입력
        }catch (IllegalArgumentException e){
            System.out.println("[ERROR] 시도 횟수는 숫자만 입력해주세요");
            getAttemptsFromUser();
        }
    }

    //자동차 생성
    private static  List<Car> createCars(String [] names) {
        List<Car> cars = new ArrayList<>();//자동차 목록 생성
        for (String name:names){
            cars.add(new Car(name)); //자동차 인스턴스화
        }
        return cars;
    }

    //경주 시작
    private static void playRacing(){

        for (int i = 0; i < attempts; i++) {
            moveCars();
            printRaceResult();
        }
    }

    //자동차들 이동
    private static void moveCars(){
        for (Car car : cars) {
            car.move();
            updateMaxScore(car);
        }
    }

    //최고 점수 업데이트
    private static void updateMaxScore(Car car){
        if (car.getPosition()>maxScore){
            maxScore= car.getPosition();
        }
    }

    //실행결과 출력
    private static void printRaceResult() {
        System.out.println("실행 결과");

        for (Car car : cars) {
            System.out.print(car.getName() + " : ");
            printDashLine(car.getPosition());
        }
        System.out.println();
    }

    //실행 결과 점수칸 출력
    private static void printDashLine(int score) {
        for (int j = 0; j < score; j++) {
            System.out.print("-");
        }
        System.out.println();
    }

    //우승자 찾기
    private static void findWinner(){
        System.out.print("최종 우승자 : ");

        String winners = cars.stream()
                .filter(car -> car.getPosition()==maxScore)
                .map(Car::getName)
                .collect(Collectors.joining(", "));
        System.out.println(winners);
    }

}

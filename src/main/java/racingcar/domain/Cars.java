package racingcar.domain;

import racingcar.domain.movestrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final int FIRST_INDEX = 0;

    private List<Car> cars;

    public Cars(List<String> carNames) {
        initCar(carNames);
    }

    public int getMaxPosition() {
        cars.sort((o1, o2) -> o2.getPosition() - o1.getPosition());

        return cars.get(FIRST_INDEX).getPosition();
    }

    public List<String> getWinner(int position) {
        return cars.stream()
                .filter(car -> car.isSamePosition(position))
                .map(car -> car.getName())
                .collect(Collectors.toList());
    }

    public void progressWithAllCar(MovingStrategy movingStrategy) {
        for (Car car : cars) {
            car.progress(movingStrategy);
        }
    }

    public int getSize() {
        return cars.size();
    }

    public Car getCar(int index) {
        return cars.get(index);
    }

    private void initCar(List<String> carNames) throws IllegalArgumentException {
        validateDuplication(carNames);

        cars = new ArrayList<>();

        for (String name : carNames) {
            cars.add(new Car(name));
        }
    }

    private void validateDuplication(List<String> carNames) {
        HashSet<String> hashSet = new HashSet<>(carNames);

        if (hashSet.size() < carNames.size()) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름이 중복되면 안됩니다.");
        }
    }
}

package com.spribe.generators;


import com.spribe.enums.Gender;
import com.spribe.enums.Role;
import com.spribe.models.request.PlayerItemDto;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@UtilityClass
public class PlayerGenerator {

    public static PlayerItemDto createRandomPlayer() {
        return createRandomPlayer(getRandomEnumValue(Gender.class), getRandomEnumValue(Role.class));
    }

    public static PlayerItemDto createRandomPlayer(Gender gender, Role role) {
        return PlayerItemDto.builder()
                .screenName(generateRandomString(10))
                .age(generateRandomIntInRange(17, 60))
                .gender(gender.name().toLowerCase())
                .login(generateRandomString(8))
                .password(generateRandomString(12))
                .role(role.name().toLowerCase())
                .build();
    }

    private static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    private static Integer generateRandomIntInRange(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[new Random().nextInt(values.length)];
    }
}

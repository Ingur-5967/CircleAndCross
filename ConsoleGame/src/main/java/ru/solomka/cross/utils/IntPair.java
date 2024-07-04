package ru.solomka.cross.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class IntPair implements Comparable<IntPair> {
    int firstNumber;
    int secondNumber;

    @Override
    public int compareTo(@NotNull IntPair pair) {
        if(this.firstNumber > pair.getFirstNumber() && this.secondNumber > pair.getSecondNumber())
            return -1;

        if(this.firstNumber < pair.getFirstNumber() && this.secondNumber < pair.getSecondNumber())
            return 0;

        return 1;
    }
}

package com.ctoutweb.aet.infra.adapter;

import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.infra.util.ListUtil;
import com.ctoutweb.aet.infra.util.NumberUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomProviderAdapter implements RandomProvider {

    @Override
    public <T extends Number> T generateRandomNumberBetweenMinAndMax(T min, T max) {
        return NumberUtil.generateRandomNumberBetweenMinAndMax(min, max);
    }

    @Override
    public <T> T selectRandomItemInList(List<T> listItems) {
        return ListUtil.selectOneRandomItemInList(listItems);
    }

    @Override
    public <T> List<T> shuffleList(List<T> listToShuffle) {
        return ListUtil.shuffledList(listToShuffle);
    }
}

package com.fastcampus.javaallinon.mycontact.repository;
/*
import com.fastcampus.javaallinon.mycontact.domain.Block;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class BlockRepositoryTest {
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void crud() {
        Block block = new Block();
        block.setName("martin");
        block.setReason("친하지않아서");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);

        List<Block> blocks = blockRepository.findAll();

        Assertions.assertThat(blocks.size()).isEqualTo(3);
        Assertions.assertThat(blocks.get(0).getName()).isEqualTo("dennis");
        Assertions.assertThat(blocks.get(1).getName()).isEqualTo("sophia");
        Assertions.assertThat(blocks.get(2).getName()).isEqualTo("martin");
    }
}*/
package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;


public class UserRepositoryTest  extends StudyApplicationTests {

    // Dependency Injection(DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public  void create(){
        String account = "Test04";
        String password = "Test04";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1111-4444";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder().account(account)
                .password(password)
                .status(status)
                .email(email).build();

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        /*
        bulider 사용
        user.setEmail("")
                .setPhoneNumber("")
                .setStatus("");

        Chain 사용
        User u = new User().setAccount("").setEmail("").setPassword("");
        */

        if(user != null){

            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("-----------------주문 묶음-----------------");
                System.out.println("수령인 : "+orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("-----------------주문 상세-----------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("주문의 상태 : " + orderDetail.getArrivalDate());
                });
            });

            Assertions.assertNotNull(user);
        }
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{ // 기존 저장한 값에서 수정된 값을 저장 한다.
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional // 실행하고 원래 상태로 되돌려 준다.
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent()); //true

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent()); // False
    }
}

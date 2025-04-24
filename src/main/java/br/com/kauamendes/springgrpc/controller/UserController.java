package br.com.kauamendes.springgrpc.controller;

import br.com.kauamendes.springgrpc.domain.User;
import br.com.kauamendes.springgrpc.repository.UserRepository;
import br.com.kauamendes.v1.user.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class UserController extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    @Override
    public void create(UserReq request, StreamObserver<UserRes> responseObserver) {
        User user = userRepository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build());
        responseObserver.onNext(user.toUserRes());
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(EmptyReq request, StreamObserver<UserResList> responseObserver) {
        List<User> users = userRepository.findAll();
        List<UserRes> usersRes = users.stream().map(User::toUserRes).toList();
        responseObserver.onNext(UserResList.newBuilder().addAllUsers(usersRes).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllServerStream(EmptyReq request, StreamObserver<UserRes> responseObserver) {
        userRepository.findAll().forEach(u -> responseObserver.onNext(u.toUserRes()));
        responseObserver.onCompleted();
    }
}

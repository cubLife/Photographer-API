package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.AvatarImageDto;
import com.gmail.serhiisemiv.dto.CarouselImageDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AvatarImageModelAssembler  implements RepresentationModelAssembler<AvatarImageDto, EntityModel<AvatarImageDto>> {
    @Override
    public EntityModel<AvatarImageDto> toModel(AvatarImageDto avatarImageDto) {
        return null;
    }
}

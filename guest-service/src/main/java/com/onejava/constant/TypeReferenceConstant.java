package com.onejava.constant;

import com.fasterxml.jackson.core.type.TypeReference;
import com.onejava.model.GuestDto;

import java.util.List;

public class TypeReferenceConstant {
    public static final TypeReference<List<GuestDto>> LIST_OF_GUEST_DTO_TYPE_REFERENCE = new TypeReference<>() {};
}

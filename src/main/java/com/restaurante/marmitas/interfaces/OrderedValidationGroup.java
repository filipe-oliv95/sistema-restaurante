package com.restaurante.marmitas.interfaces;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({NotBlankGroup.class, SizeGroup.class, Default.class, NotNullGroup.class, PositiveNumberGroup.class})
public interface OrderedValidationGroup {}

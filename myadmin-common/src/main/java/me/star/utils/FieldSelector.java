package me.star.utils;


public interface FieldSelector<Entity, FieldType> {
    FieldType select(Entity type);
}

package com.social.media.SocialMedia.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String className, String field, String fieldValue){
        super(String.format("%s is not found with %s : %s",className,field,fieldValue));
    }
}
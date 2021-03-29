package io.hayk.rsocketdemo.rest.common;

import io.hayk.rsocketdemo.security.auth.external.ExternalAccountDetailsResolverProvider;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.*;

import java.util.stream.Collectors;

import static java.lang.String.format;

public abstract class BaseController {

    private final ExternalAccountDetailsResolverProvider externalAccountDetailsResolverProvider;

    private final Validator validator;

    public BaseController(final ExternalAccountDetailsResolverProvider externalAccountDetailsResolverProvider,
                          final Validator validator) {
        this.externalAccountDetailsResolverProvider = externalAccountDetailsResolverProvider;
        this.validator = validator;
    }

    protected void validate(final Object target) {
        final Errors errors = new BeanPropertyBindingResult(
                target,
                "");
        validator.validate(target, errors);
        if (errors.hasErrors()) {
            throw new ValidationFailedException(
                    errors.getAllErrors().stream()
                            .map(this::messageFor)
                            .collect(Collectors.toList())
            );
        }
    }

    private String messageFor(final ObjectError objectError) {
        if (objectError instanceof FieldError) {
            final FieldError fieldError = (FieldError) objectError;
            return format("%s - %s - %s", fieldError.getField(), fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage());
        }
        return objectError.getDefaultMessage();
    }

    protected ExternalAccountDetails extractExternalAccountDetails(final OAuth2AuthenticationToken token) {
        return externalAccountDetailsResolverProvider
                .forAuthorizedClientRegistrationId(token.getAuthorizedClientRegistrationId())
                .orElseThrow(() -> new IllegalStateException("No account details extractor was registered for  " + token.getAuthorizedClientRegistrationId()))
                .resolve(token);
    }
}

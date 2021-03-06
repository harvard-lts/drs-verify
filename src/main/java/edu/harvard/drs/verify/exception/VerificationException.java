/**
 * Copyright (c) 2021 President and Fellows of Harvard College
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.harvard.drs.verify.exception;

import edu.harvard.drs.verify.dto.VerificationError;
import java.util.Map;

/**
 * Verification exception.
 */
public class VerificationException extends Exception {

    private final Map<String, VerificationError> errors;

    /**
     * Verification exception from errors.
     *
     * @param errors verification errors
     */
    public VerificationException(Map<String, VerificationError> errors) {
        super("Verification failed");
        this.errors = errors;
    }

    public Map<String, VerificationError> getErrors() {
        return errors;
    }

}

package ca.uhn.fhir.rest.server.exceptions;

/*
 * #%L
 * HAPI FHIR - Core Library
 * %%
 * Copyright (C) 2014 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu.resource.OperationOutcome;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.server.Constants;

/**
 * Represents an <b>HTTP 404 Resource Not Found</b> response, which means that the request is pointing to a resource that does not exist.
 */
public class ResourceNotFoundException extends BaseServerResponseException {

	private static final long serialVersionUID = 1L;

	public static final int STATUS_CODE = Constants.STATUS_HTTP_404_NOT_FOUND;

	public ResourceNotFoundException(Class<? extends IResource> theClass, IdDt theId) {
		super(STATUS_CODE, createErrorMessage(theClass, theId));
	}

	public ResourceNotFoundException(Class<? extends IResource> theClass, IdDt theId, OperationOutcome theOperationOutcome) {
		super(STATUS_CODE, createErrorMessage(theClass, theId), theOperationOutcome);
	}

	/**
	 * @deprecated This doesn't make sense, since an identifier is not a resource ID and shouldn't generate a 404 if it isn't found - Should be removed
	 */
	public ResourceNotFoundException(Class<? extends IResource> theClass, IdentifierDt theId) {
		super(STATUS_CODE, "Resource of type " + theClass.getSimpleName() + " with ID " + theId + " is not known");
	}

	public ResourceNotFoundException(IdDt theId) {
		super(STATUS_CODE, createErrorMessage(theId));
	}

	public ResourceNotFoundException(IdDt theId, OperationOutcome theOperationOutcome) {
		super(STATUS_CODE, createErrorMessage(theId), theOperationOutcome);
	}

	public ResourceNotFoundException(String theMessage) {
		super(STATUS_CODE, theMessage);
	}

	private static String createErrorMessage(Class<? extends IResource> theClass, IdDt theId) {
		return "Resource of type " + theClass.getSimpleName() + " with ID " + theId + " is not known";
	}

	private static String createErrorMessage(IdDt theId) {
		return "Resource " + (theId != null ? theId.getValue() : "") + " is not known";
	}

}

package models;

import java.util.Map;

public class ErrorMessageModel {





        private String fieldName;
        private String errorMessage;

        public ErrorMessageModel() {}

        public String getFieldname() {
            return fieldName;
        }

        public  void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

      public  void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
      }
        public static ErrorMessageModel createErrorModel(Map<String, String> entry) {

            ErrorMessageModel errorMessageModel = new ErrorMessageModel();

            //Table of the feature file
            errorMessageModel.setFieldName(entry.get("field"));
            errorMessageModel.setErrorMessage(entry.get("message"));

            return errorMessageModel;
        }
    }



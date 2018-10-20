(ns woolybear.ad.catalog.forms
  "Catalog and acu/demonstrations of available form and form-field components."
  (:require [re-frame.core :as re-frame]
            [woolybear.ad.catalog.utils :as acu]
            [woolybear.ad.layout :as layout]
            [woolybear.ad.containers :as containers]
            [woolybear.ad.forms :as forms]))

(re-frame/reg-sub
  :ad-catalog/forms-demo
  :<- [:db/ad-catalog]
  (fn [catalog]
    (:forms-demo catalog)))

(re-frame/reg-sub
  :forms-demo/demo
  :<- [:ad-catalog/forms-demo]
  (fn [forms-demo ix]
    (get forms-demo ix)))

(defn catalog
  []

  [:div

   (acu/demo "Form field labels (simple)"
     "You can add label components as children of input components, and they will be applied
     appropriately and consistently to the fields they describe. Labels take the standard
     extra-classes and subscribe-to-classes options."
     [layout/padded
      [forms/label "Simple Label"]]
     '[layout/padded
       [forms/label "Simple Label"]])

   (acu/demo "Form field labels (required)"
     "Form field label components also support a :required? option which, if present and truthy,
     will add a red asterisk to the label to mark the corresponding field as required."
     [layout/padded
      [forms/label {:required? true} "Required Label"]]
     '[layout/padded
       [forms/label {:required? true} "Required Label"]])

   (acu/demo "Simple text input"
     "Text input components are \"self-aware,\" meaning that when you create a text input, you pass it the
     path to its component data inside the app-db. The component uses this information to initialize its
     internal state, and to provide built-in functionality including on-change event handling and flags for
     :visited? and :active? that can be used in form validation. As a second required option, you must also
     provide a subscription that returns this component data.

     Text inputs support a number of additional options, including the standard :extra-classes and
     :subscribe-to-classes options. See `(doc woolybear.ad.forms/text-input)` for details, or refer to this
     example and those that follow."

     [layout/padded
      [forms/field-group
       [forms/label {:for "demo-1"} "Simple Text Input"]
       [forms/text-input {:id                          "demo-1"
                          :component-data-path         [:ad-catalog :forms-demo 1]
                          :subscribe-to-component-data [:forms-demo/demo 1]}]]]
     '[layout/padded
       [forms/field-group
        [forms/label {:for "demo-1"} "Simple Text Input"]
        [forms/text-input {:id                          "demo-1"
                           :component-data-path         [:ad-catalog :forms-demo 1]
                           :subscribe-to-component-data [:forms-demo/demo 1]}]]])

   (acu/demo "Password input"
     "If you use a text-input component with a :type option of :password, the field will display
     a standard password input instead of a regular text input."

     [layout/padded
      [forms/field-group
       [forms/label {:for "demo-2"} "Password Input"]
       [forms/text-input {:id                          "demo-2"
                          :type                        :password
                          :component-data-path         [:ad-catalog :forms-demo 2]
                          :subscribe-to-component-data [:forms-demo/demo 2]}]]]
     '[layout/padded
       [forms/field-group
        [forms/label {:for "demo-2"} "Password Input"]
        [forms/text-input {:id                          "demo-2"
                           :type                        :password
                           :component-data-path         [:ad-catalog :forms-demo 2]
                           :subscribe-to-component-data [:forms-demo/demo 2]}]]])

   (acu/demo "Text input with placeholder"
     "Use a placeholder instead of a label if you like."
     [layout/padded
      [forms/text-input {:id "demo-3"
                         :placeholder "your-email@your-company.com"
                         :component-data-path         [:ad-catalog :forms-demo 3]
                         :subscribe-to-component-data [:forms-demo/demo 3]}]]
     '[layout/padded
       [forms/text-input {:id "demo-3"
                          :placeholder "your-email@your-company.com"
                          :component-data-path         [:ad-catalog :forms-demo 3]
                          :subscribe-to-component-data [:forms-demo/demo 3]}]])

   (acu/demo "Text input with errors"
     "To validate form fields, create a subscription that subscribes to the field's component data.
     This will give you a map with the keys :value (current value), :visited? (whether the field
     has ever been interacted with), :active? (whether the field currently has the focus), :path
     (self-referential path), and :original-value (value passed to the field when it was first
     created). Use these values to return a vector of error messages, or nil if no errors. Each
     error message should be a map with the keys :msg and :class, where the :msg value is the
     text string to display, and :class is either :error, :warning, or :info."

     [layout/padded
      [forms/field-group
       [forms/label {:for "demo-4-a"} "Field with errors"]
       [forms/text-input {:id "demo-4-a"
                          :subscribe-to-errors (atom [{:msg "This is an error message" :class :error}])
                          :component-data-path [:ad-catalog :forms-demo "4a"]
                          :subscribe-to-component-data [:forms-demo/demo "4a"]}]]
      [forms/field-group
       [forms/label {:for "demo-4-b"} "Field with warning"]
       [forms/text-input {:id "demo-4-b"
                          :subscribe-to-errors (atom [{:msg "This is warning" :class :warning}])
                          :component-data-path [:ad-catalog :forms-demo "4b"]
                          :subscribe-to-component-data [:forms-demo/demo "4b"]}]]
      [forms/field-group
       [forms/label {:for "demo-4-c"} "Field with info"]
       [forms/text-input {:id "demo-4-c"
                          :subscribe-to-errors (atom [{:msg "This is just FYI" :class :info}])
                          :component-data-path [:ad-catalog :forms-demo "4c"]
                          :subscribe-to-component-data [:forms-demo/demo "4c"]}]]]
     '[layout/padded
      [forms/field-group
       [forms/label {:for "demo-4-a"} "Field with errors"]
       [forms/text-input {:id "demo-4-a"
                          :subscribe-to-errors (atom [{:msg "This is an error message" :class :error}])
                          :component-data-path [:ad-catalog :forms-demo "4a"]
                          :subscribe-to-component-data [:forms-demo/demo "4a"]}]]
      [forms/field-group
       [forms/label {:for "demo-4-b"} "Field with warning"]
       [forms/text-input {:id "demo-4-b"
                          :subscribe-to-errors (atom [{:msg "This is warning" :class :warning}])
                          :component-data-path [:ad-catalog :forms-demo "4b"]
                          :subscribe-to-component-data [:forms-demo/demo "4b"]}]]
      [forms/field-group
       [forms/label {:for "demo-4-c"} "Field with info"]
       [forms/text-input {:id "demo-4-c"
                          :subscribe-to-errors (atom [{:msg "This is just FYI" :class :info}])
                          :component-data-path [:ad-catalog :forms-demo "4c"]
                          :subscribe-to-component-data [:forms-demo/demo "4c"]}]]])



   ])

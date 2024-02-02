import React from 'react';
import styles from './Input.module.css';

function Input({ type, text, name, placeholder, handleOnChange, value, error }) {
  const showError = error && error.length > 0;
  const isPriceField = name === 'price';

  return (
    <div className={`${styles.form_control} ${showError ? styles.error : ''}`}>
      <label htmlFor={name}>{text}:</label>
      <div className={`${styles.input_container} ${isPriceField ? styles.priceInput : ''}`}>
        <input
          type={type}
          name={name}
          id={name}
          placeholder={placeholder}
          onChange={handleOnChange}
          value={value}
          className={`${showError ? styles.inputError : ''} ${styles.widerInput}`}
        />
      </div>
      {showError && <span className={styles.errorMessage}>{error}</span>}
    </div>
  );
}

export default Input;

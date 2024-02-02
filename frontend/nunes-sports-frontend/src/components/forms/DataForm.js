import { useState } from "react";
import styles from "./ProductForm.module.css";
import Input from "./Input";
import SubmitButton from "../buttons/SubmitButton";

function DataForm({ handleSubmit, btnText, productData }) {
  const [product, setProduct] = useState(productData || {});
  const [errors, setErrors] = useState({});

  const validateForm = () => {
    const newErrors = {};

    if (!product.name) {
      newErrors.name = "O nome é obrigatório.";
    }

    if (!product.description) {
      newErrors.description = "A descrição é obrigatória.";
    }

    if (!product.price) {
      newErrors.price = "O preço é obrigatório.";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const submit = (e) => {
    e.preventDefault();

    if (validateForm()) {
      handleSubmit(product);
    }
  };

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: undefined });
  };

  return (
    <form onSubmit={submit} className={styles.form}>
      <Input
        type="date"
        text="Data para montagem do time"
        handleOnChange={handleChange}
        value={product.name || ""}
        name="name"
        error={errors.name}
      />
      <SubmitButton text={btnText} />
    </form>
  );
}

export default DataForm;

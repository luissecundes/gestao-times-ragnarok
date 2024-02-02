import { useState } from "react";
import styles from "./ProductForm.module.css";
import Input from "./Input";
import SubmitButton from "../buttons/SubmitButton";

function IntegranteForm({ handleSubmit, btnText, productData }) {
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
        type="text"
        text="Nome do Integrante"
        placeholder="Insira apenas o primeiro nome"
        handleOnChange={handleChange}
        value={product.name || ""}
        name="name"
        error={errors.name}
      />
      <Input
        type="text"
        text="Franquia do Integrante"
        placeholder="Franquia A, B, C ou D."
        handleOnChange={handleChange}
        value={product.description || ""}
        name="description"
        error={errors.description}
      />
      <Input
        type="text"
        text="Função do Integrante"
        placeholder="Champion, Cavaleiro, Sumo-Sacerdote ou Algoz"
        handleOnChange={handleChange}
        value={product.price || ""}
        name="price"
        error={errors.price}
      />
      <SubmitButton text={btnText} />
    </form>
  );
}

export default IntegranteForm;

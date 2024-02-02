import { useState, useEffect } from "react";
import styles from "./ProductEditForm.module.css";
import Input from "./Input";
import SubmitButton from "../buttons/SubmitButton";

function ProductEditForm({ handleSubmit, btnText, productId }) {
  const [product, setProduct] = useState({
    name: "",
    description: "",
    price: "",
  });

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await fetch(`http://localhost:8080/products/${productId}`);
        const data = await response.json();
        setProduct(data);
      } catch (error) {
        console.error('Erro ao buscar produto:', error);
      }
    };

    fetchProduct();
  }, [productId]);

  const submit = (e) => {
    e.preventDefault();
    handleSubmit(product);
  };

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  return (
    <div className={styles.producteditform_container}>
      <h1>Editar Produto</h1>
      <form onSubmit={submit} className={styles.form}>
        <Input
          type="text"
          text="Nome do Produto"
          placeholder="Insira o nome do produto"
          handleOnChange={handleChange}
          value={product.name || ""}
          name="name"
        />
        <Input
          type="text"
          text="Descrição do Produto"
          placeholder="Insira a descrição do produto"
          handleOnChange={handleChange}
          value={product.description || ""}
          name="description"
        />
        <Input
          type="number"
          text="Preço do Produto"
          placeholder="Insira o preço do produto"
          handleOnChange={handleChange}
          value={product.price || ""}
          name="price"
        />
        <SubmitButton text={btnText} />
      </form>
    </div>
  );
}

export default ProductEditForm;

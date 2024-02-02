import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './AddProducts.module.css';
import DataForm from '../forms/DataForm';
import LoadingSpinner from './LoadingSpinner'; 

function AddDatas() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false); 
  const handleProductSubmit = async (product) => {
    try {
      setLoading(true); 

      const response = await createProduct(product);
      console.log(response);
      navigate('/datas');
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false); 
    }
  };

  const createProduct = async (product) => {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: product.name,
        description: product.description,
        price: product.price,
      }),
    };

    const response = await fetch('http://localhost:8080/products', requestOptions);

    const contentType = response.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
      const result = await response.json();

      return result;
    } else {
      return response.text();
    }
  };

  return (
    <div className={styles.addproducts_container}>
      <h1>Adicionar Integrantes</h1>
      <p>Preencha os dados para adicionar um novo integrante</p>
      {loading ? (
        <LoadingSpinner />
      ) : (
        <DataForm handleSubmit={handleProductSubmit} btnText="Adicionar Integrante" />
      )}
    </div>
  );
}

export default AddDatas;

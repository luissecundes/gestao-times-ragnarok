import React from 'react';
import styles from './ProductTable.module.css';
import DeleteButton from '../buttons/DeleteButton';
import EditButton from '../buttons/EditButton';

function ProductTable({ productList, onDelete, onEdit }) {
  return (
    <table className={styles.ProductsTable}>
      <thead>
        <tr className={styles.titles}>
          <th>Cód. do Time</th>
          <th>Integrantes</th>
          <th>Data da Partida</th>
        </tr>
      </thead>
      <tbody>
        {productList.map((product) => (
          <tr key={product.id} className={styles.ProductItem}>
            <td className={styles.ProductId}>{product.id || 'N/A'}</td>
            <td className={styles.ProductName}>{product.name}</td>
            <td className={styles.ProductDescription}>{product.description}</td>
            <td>R${product.price.toFixed(2)}</td>
            <td>
              <DeleteButton onClick={() => onDelete(product.id)} />
              <EditButton onClick={() => onEdit(product.id)} />
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default ProductTable;

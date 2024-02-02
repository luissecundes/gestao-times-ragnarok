import React, { useState } from 'react';

function DeleteProducts({ onDelete, onClose }) {
  const [productId, setProductId] = useState('');

  const handleDelete = () => {
    onDelete(productId);
    onClose(); 
  };

  return (
    <div>
      <p>Digite o ID do produto que deseja excluir:</p>
      <input
        type="text"
        value={productId}
        onChange={(e) => setProductId(e.target.value)}
      />
      <button onClick={handleDelete}>Excluir Produto</button>
    </div>
  );
}

export default DeleteProducts;

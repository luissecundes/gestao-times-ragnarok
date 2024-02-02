import React from 'react';
import EditIcon from '@mui/icons-material/Edit';
import styles from './EditButton.module.css'; 


function EditButton({ onClick }) {
  return (
    <button onClick={onClick} className={styles.editButton}>
      <EditIcon />
    </button>
  );
}

export default EditButton;

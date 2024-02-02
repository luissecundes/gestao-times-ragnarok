import React from 'react';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

function DeleteButton({ onClick }) {
  return (
    <IconButton onClick={onClick} color="error" size="small">
      <DeleteIcon />
    </IconButton>
  );
}

export default DeleteButton;

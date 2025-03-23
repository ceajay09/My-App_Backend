import * as React from 'react';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Link from '@mui/material/Link';
import Box from '@mui/material/Box';

interface MainFeaturedPostProps {
  post: {
    description: string;
    image: string;
    imageText: string;
    linkText: string;
    title: string;
  };
}

export default function MainFeaturedPost(props: MainFeaturedPostProps) {
  const { post } = props;

  return (
    <Paper sx={{
      position: 'relative',
      color: '#fff',
      mb: 4,
      overflow: 'hidden',
      padding: '30px',
      textAlign: 'center'
    }}>
      <img src={post.image} alt={post.imageText} style={{
        maxWidth: '100%', 
        width: 'auto',
        height: 'auto',    
        maxHeight: '300px',  
        display: 'block',
        margin: 'auto',
      }} />
      <Box sx={{
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: 'rgba(0,0,0,.05)', 
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'flex-end',
        alignItems: 'flex-start',
        p: { xs: 2, md: 3 }, 
      }}>

        <Typography component="h1" variant="h3" color="inherit" gutterBottom sx={{
          opacity: 0, 
          zIndex: 2, 
        }}>
          {post.title}
        </Typography>
        <Typography variant="h5" color="inherit" paragraph sx={{
          opacity: 0, 
          zIndex: 2, 
        }}>
          {post.description}
        </Typography>
        <Link variant="subtitle1" href="/aboutMe" sx={{
          zIndex: 2, 
        }}>
          {post.linkText}
        </Link>
      </Box>
    </Paper>
  );
}

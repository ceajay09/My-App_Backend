import * as React from 'react';
import { Container, Grid, Paper, Box, Typography, CssBaseline, ThemeProvider } from '@mui/material';
import { createTheme } from '@mui/material/styles';
import Layout from './Layout';

const theme = createTheme();

export default function TechInsights() {
  return (
    <Layout>
      <Container maxWidth="lg">
        <Typography variant="h3" align="center" paddingTop={2} gutterBottom>
          TechInsights
        </Typography>

        <Box sx={{ my: 4 }}>
          <Paper elevation={4} sx={{ p: 2, border: '2px solid', bgcolor: 'background.paper' }}>
            <Typography variant="h6" gutterBottom sx={{ textDecoration: 'underline' }}>
              Purpose of the Website
            </Typography>
            <Typography variant="subtitle1" paragraph sx={{ fontStyle: 'italic' }}>
              This blog is dedicated to providing tips and advice for job applications.
            </Typography>
          </Paper>
        </Box>

        <Box sx={{ my: 4 }}>
          <Paper elevation={4} sx={{ p: 2, border: '2px solid', bgcolor: 'background.paper' }}>
            <Typography variant="h6" gutterBottom sx={{ textDecoration: 'underline' }}>
              Contact Information
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={1}>
                <Typography variant="subtitle1" paragraph sx={{ fontStyle: 'italic' }}>
                  Owner:
                </Typography>
              </Grid>
              <Grid item xs={11}>
                <Typography variant="subtitle1" paragraph sx={{ fontStyle: 'italic' }}>
                  César Jaquiéry
                </Typography>
              </Grid>
              <Grid item xs={1}>
                <Typography variant="subtitle1" paragraph sx={{ fontStyle: 'italic' }}>
                  Email:
                </Typography>
              </Grid>
              <Grid item xs={11}>
                <Typography variant="subtitle1" paragraph sx={{ fontStyle: 'italic' }}>
                  c.jaquiery@gmail.com
                </Typography>
              </Grid>
            </Grid>
          </Paper>
          <Box sx={{ my: 4 }}>
            <Paper elevation={4} sx={{ p: 2, border: '2px solid', bgcolor: 'background.paper' }}>
              <Typography variant="h6" gutterBottom sx={{ textDecoration: 'underline' }}>
                Disclaimer
              </Typography>
              <Typography variant="subtitle1" paragraph sx={{ fontStyle: 'italic' }}>
                Information on this website is reviewed regularly but cannot guarantee its completeness, accuracy, or currentness.
              </Typography>
            </Paper>
          </Box>
        </Box>
      </Container>
    </Layout >
  );
}